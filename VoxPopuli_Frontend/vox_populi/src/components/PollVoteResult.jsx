import { useParams } from "react-router";
import { useState, useEffect, useContext } from "react";
import { getPollResults, getPoll, getUserVoteOnPoll } from "../scripts/apicalls";
import { Link } from "react-router";
import UserContext from "../contexts/CreateUserContext";
import Loading from "./Loading";
import ErrorMessage from "./Error";
import PieChart from "./PollResultPieGraph";
import * as d3 from "d3";

function PollVoteResult() {
    const { id } = useParams();
    const { userData } = useContext(UserContext);

    const [poll, setPoll] = useState(null);
    const [userVote, setUserVote] = useState(null);
    const [pollResults, setPollResults] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    // Gets the colors for the squares next to the vote labels
    const color = d3.scaleOrdinal(d3.schemeCategory10);

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

    // Call API to get poll result.
    useEffect(() => {
        const loadPoll = async () => {
            try {
                const data = await getPoll(pollId);
                setPoll(data);
            } catch (err) {
                setError(err);
            }
        }

        const loadResults = async () => {
            try {
                const data = await getPollResults(pollId);
                setPollResults(data);
            } catch (err) {
                setError(err);
            }
        }

        const loadUsersVote = async () => {
            try {
                const data = await getUserVoteOnPoll(pollId, userData);
                setUserVote(data);
            } catch (err) {
                setError(err);
            }
        }
     
        loadPoll();
        loadResults();
        loadUsersVote();
        setLoading(false);
    }, []); // empty array, only run effect on first load
    if (error) {
        return ErrorMessage(error);
    }

    if (loading || !poll || !userData || !userVote) {
        return Loading();
    }
    




    return (
        <>
            <div className="container">
                <div className="row mb-5 justify-content-center">
                    <div className="col-md-10">
                        <div className="row mt-3 mb-3 justify-content-center">
                            <h2>{poll.pollTitle}</h2>
                            <h5 className="text-muted">{poll.pollDescription}</h5>
                            <h5>You voted for: {userVote.optionName}</h5>
                        </div>

                        <div className="row justify-content-center">
                            <div className="col-md-3">
                                {pollResults.map((option, i) => (
                                    <h5 
                                    className="pt-3"
                                    key={i}
                                    >
                                        {i + 1}. {option.optionName} - {option.optionVotes} Votes <span style={{color:color(i % 10), fontSize:"1.6rem"}}>■</span>
                                    </h5>
                                ))}
                            </div> 
                            <div className="col-md-9 mt-3">
                                <PieChart
                                    data={pollResults}
                                />
                            </div>
                        </div>
                    </div>
                    <Link type="button" className='btn btn-primary mt-5 w-50' to={'/explorePolls'}>Explore More Polls</Link>
                </div>
            </div>
        </>
    )
    
}

export default PollVoteResult;