import { useParams, Navigate } from "react-router";
import Loading from "./Loading";
import ErrorMessage from "./Error";
import { useContext, useEffect, useState } from "react";
import { getPoll, getUserVoteOnPoll, voteOnPoll } from "../scripts/apicalls";
import UserContext from "../contexts/CreateUserContext";

function ViewPoll() {
    const { id } = useParams();
    const { userData } = useContext(UserContext);

    const [poll, setPoll] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [showInvalidVoteMessage, setShowInvalidVoteMessage] = useState(false);
    const [selectedOption, setSelectedOption] = useState(null);
    const [redirectUserToResults, setRedirectUserToResults] = useState(false);


    // Attempt to parse id for poll.
    let pollId;
    try {
        pollId = parseInt(id);

        if (isNaN(pollId)) {
            throw new Error('Invalid ID format!')
        }
    } catch (error) {
        return ErrorMessage(error);
    }

    // Call API to get poll data.
    useEffect(() => {
        // First, let's check if the user has already voted on this poll.
        const hasAlreadyVoted = async () => {
            try {
                const hasVoted = await getUserVoteOnPoll(pollId, userData);
                if (hasVoted) {
                    setRedirectUserToResults(true);
                }
            } catch (err) {
                setError(err);
            }
        }


        const loadPoll = async () => {
            try {
                const data = await getPoll(pollId);
                setPoll(data);
            } catch (err) {
                setError(err);
            } finally {
                setLoading(false);
            }
        }
     
        hasAlreadyVoted();
        loadPoll();
    }, []); // empty array, only run effect on first load


    const handleSubmit = async (e) => {
            e.preventDefault()
            
            const formData = new FormData(e.target);
            const pollVote = {
                userId : userData.userId,
                pollId : poll.pollId,
                optionNumber : poll.options[selectedOption].optionNumber
            };
    
            try {
                await voteOnPoll(pollVote);
                setRedirectUserToResults(true); // If the voteOnPoll API call didn't cause error, then we can redirect the user to the results.
            } catch (err) {
                setShowInvalidVoteMessage(true);
            }
        };

    if (error) {
        return ErrorMessage(error)
    }

    if (loading || !poll) {
        return Loading();
    }

    if (redirectUserToResults) {
        return ( <Navigate to={`/pollVoteResult/${pollId}`}/>)
    }

    return (
        <div className="container mt-2">
            <div className="card shadow p-4">
                {/* Poll Title and Description */}
                <div className="row mb-5">
                <div className="col-12 text-center">
                    <h2 className="fw-bold">{poll.pollTitle}</h2>
                    <h4 className="text-muted">{poll.pollDescription}</h4>
                </div>
                </div>

                <form onSubmit={handleSubmit}>
                <div className="row justify-content-center">
                    {poll.options.map((option, i) => {
                    // Only render every 2 options as a row
                    if (i % 2 !== 0) return null;

                    const nextOption = poll.options[i + 1]; // Check if there's a option after this option.

                    return (
                        <div className="row mb-3 justify-content-center" key={i}>
                        {/* First Button */}
                        <div className={nextOption ? "col-md-5 mb-2" : "col-md-5 col-8 mb-2 fluid-vote-button"}>
                            <button
                            type="button"
                            className={`btn poll-option-button btn-secondary w-100 ${
                                    selectedOption === i ? "selected-poll-option-button" : ""
                                }`}
                            onClick={() => setSelectedOption(i)}
                            >
                            {option.optionName}
                            </button>
                        </div>

                        {/* Second Button (if exists) */}
                        {nextOption && (
                            <div className="col-md-5 mb-2 ">
                            <button
                                type="button"
                                className={`btn poll-option-button btn-secondary w-100 ${
                                    selectedOption === i+1 ? "selected-poll-option-button" : ""
                                    }`}
                                onClick={() => 
                                    setSelectedOption(i + 1)}
                            >
                                {nextOption.optionName}
                            </button>
                            </div>
                        )}
                        </div>
                    );
                    })}

                    {/* Submit Button */}
                    <div className="col-12 col-md-6 mt-4 text-center">
                    <button
                        type="submit"
                        className="btn btn-primary poll-vote-submit-button w-100"
                    >
                        Submit Vote
                    </button>
                    </div>
                </div>
                </form>
                {showInvalidVoteMessage && (
                    ErrorMessage("You've already voted on this poll!")
                )}
            </div>
        </div>
    )
}

export default ViewPoll;