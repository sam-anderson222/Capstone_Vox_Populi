import { useState } from "react";
import { Link } from "react-router";
import UserContext from "../contexts/CreateUserContext";
import { useContext } from "react";
import { Navigate } from "react-router";
import SuccessMessage from "./Success";
import { attemptSignUp } from "../scripts/apicalls";

function SignUp() {
    const [username, setUsername] = useState("");
    const [userPassword, setUserPassword] = useState("");
    const [error, setError] = useState(null);
    const [showSuccessMessage, setShowSuccessMessage] = useState(false);

    const maxUsernamePasswordLen = 30;

    // 'e' is is the automatically create event handler object.
    const handleSubmit = async (e) => {
        e.preventDefault()
        
        const userSignUpData = {
            username: username,
            userPassword: userPassword
        };

        try {
            await attemptSignUp(userSignUpData);
            setShowSuccessMessage(true);
        } catch (err) {
            setError(err);
        }


    };

    if (showSuccessMessage) {
        return SuccessMessage("Account Successfully Created. Proceed to Login Page.")
    }

    return (
        <div className="container">
            <div className="row mb-5 justify-content-center w-80">
                <div className="col-md-5">

                        <h2>Sign Up</h2>
                        <form onSubmit={handleSubmit}>
                            <div className="row mt-5 mb-5 justify-content-center">
                                <label>Username: </label>
                                <input 
                                    name='_user'
                                    className="form-control"
                                    type="text"
                                    value={username}
                                    onChange={(e) => {setUsername(e.target.value)}}
                                    maxLength={maxUsernamePasswordLen}
                                />
                            </div>

                            <div className="row mb-5 justify-content-center">
                                <label>Password: </label>
                                <input 
                                    name='_pass'
                                    className="form-control"
                                    type="text"
                                    value={userPassword}
                                    onChange={(e) => {setUserPassword(e.target.value)}}
                                    maxLength={maxUsernamePasswordLen}
                                />
                            </div>

                            <div className="row mb-5 justify-content-center">
                                {error && (
                                    <div className="alert alert-warning mt-2" role="alert">Error: Could Not Create Account. Please Try Again</div>
                                )}
                            </div>

                            <div className="row mb-5 justify-content-center">
                                <button type="submit" className="btn btn-primary mb-2">
                                    Sign Up
                                </button>
                                <Link to='/logIn' className="text-center text-secondary mt-2">
                                    Already Have An Account
                                </Link>
                            </div>
                        </form>
                </div>
            </div>
        </div>
    )



}

export default SignUp;
