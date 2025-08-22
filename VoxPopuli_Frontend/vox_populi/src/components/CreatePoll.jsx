import { useState, useContext } from "react";
import { createNewPoll } from "../scripts/apicalls";
import UserContext from "../contexts/CreateUserContext";
import SuccessMessage from "./Success";



function CreatePoll() {
    const { userData } = useContext(UserContext);

    const [pollTitleText, setPollTitleText] = useState("");
    const [pollDescriptionText, setPollDescriptionText] = useState("");
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const [redirectToSuccessPage, setRedirectToSuccessPage] = useState(false);
    const [pollOptions, setPollOptions] = useState(["", ""]);

    const maxTitleLength = 100;
    const maxDescriptionLength = 200;
    const maxOptionLength = 50;

    const minNumberOfOptions = 2;
    const maxNumberOfOptions = 20;

    // We need to turn the data into what will become a java poll object.

    const addOption = () => {
        if (pollOptions.length < maxNumberOfOptions) {
            setPollOptions([...pollOptions, ""]);
        }
    }   
    
    const removeOption = (index) => {
        if (pollOptions.length >= minNumberOfOptions) {
            setPollOptions(pollOptions.filter((_, i) => i !== index));
        }
    }

    const updateOption = (index, value) => {
        const updated = [...pollOptions];
        updated[index] = value;
        setPollOptions(updated);
    }

    // Turn polls options text into json objects.
    const convertPollOptionsToJson = () => {
        let addedOptions = 0; // addedOptions keeps the optionNumber nice while also ensuring that empty options aren't submitted.
        let converted = [];
        pollOptions.map((option, i) => {
            if (option !== "") { // Ignore empty options
                addedOptions += 1;
                converted.push({optionNumber : addedOptions, optionName : option})
            }
        })

        return converted;
    }

    const handleSubmit = async (e) => {
            e.preventDefault()
            

            const createPollData = {
                pollAuthor: userData.userId,
                pollTitle: pollTitleText,
                pollDescription: pollDescriptionText,
                options: convertPollOptionsToJson()
            };

            console.log(createPollData);
            try {
                const success = await createNewPoll(createPollData);
                if (success) {
                    setRedirectToSuccessPage(true);
                }
            } catch (err) {
                setShowErrorMessage(true);
            }
    
        };

    if (redirectToSuccessPage) {
        return SuccessMessage("Poll Success Created. You can now find it on the explore page!")
    }

    return (
        <>
            <div className="container">
                <div className="row mb-5 justify-content-center w-80">
                    <div className="col-md-10 mt-2">

                            <h2>Create Poll</h2>
                            <form onSubmit={handleSubmit} autoComplete="off">

                                {/* Poll Title */}
                                <div className="row mt-5 mb-2 justify-content-center">
                                    <label className="form-label">Title: </label>
                                    <input
                                        name='title'
                                        className="form-control"
                                        type="text"
                                        maxLength={maxTitleLength}
                                        value={pollTitleText}
                                        onChange={(e) => setPollTitleText(e.target.value)}
                                    />
                                    <div className="form-text text-end">
                                    {pollTitleText.length}/{maxTitleLength}
                                    </div>
                                </div>


                                {/* Poll Description */}
                                <div className="row mb-2 justify-content-center">
                                    <label className="form-label">Description: </label>
                                    <textarea
                                        name='description'
                                        className="form-control"
                                        rows="4"
                                        type="textarea"
                                        maxLength={maxDescriptionLength}
                                        value={pollDescriptionText}
                                        onChange={(e) => setPollDescriptionText(e.target.value)}
                                    />
                                    <div className="form-text text-end">
                                        {pollDescriptionText.length}/{maxDescriptionLength}
                                    </div>
                                </div>


                                {/* Poll Options */}
                                <div className="row mb-5 justify-content-start w-50">
                                    <label className="form-label">Options (2-20): </label>
                                    {pollOptions.map((option, index) => (
                                        <div key={index} className="d-flex">
                                            <input
                                                name='option'
                                                className="form-control mt-2 mb-2 pt-2 pb-2"
                                                type="text"
                                                maxLength={maxOptionLength}
                                                value={pollOptions[index]}
                                                onChange={(e) => updateOption(index, e.target.value)}
                                            />
                                            <button
                                                type="button"
                                                className="btn btn-outline-danger"
                                                onClick={() => removeOption(index)}
                                                style={{ width: "3rem", height: "3rem" }}
                                                disabled={pollOptions.length <= minNumberOfOptions}
                                            >
                                                ✕
                                            </button>
                                        </div>    
                                    ))}
                                    <button
                                        type="button"
                                        className="btn btn-outline-primary mt-2"
                                        style={{ width: "3rem", height: "3rem" }}
                                        disabled={pollOptions.length >= maxNumberOfOptions}
                                        onClick={addOption}
                                    >
                                        ＋
                                    </button>
                                </div>
                                
                                <div className="row mb-5 justify-content-center">
                                    {showErrorMessage && (
                                        <div className="alert alert-warning mt-2" role="alert">Error: Could Not Create Poll.</div>
                                    )}
                                </div>

                                <div className="row mb-5 justify-content-center">
                                    <button type="submit" className="btn btn-primary w-50">
                                        Create Poll
                                    </button>
                                </div>
                            </form>
                    </div>
                </div>
            </div>
        </>
    )
}

export default CreatePoll;