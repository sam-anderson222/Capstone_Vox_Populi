import { useState } from "react";
import { attemptLogIn } from "../scripts/apicalls";
import UserContext from "../contexts/CreateUserContext";
import { useContext } from "react";
import { Navigate } from "react-router";

function LogIn() {
    const { login } = useContext(UserContext);
    const [showErrorMessage, setShowErrorMessage] = useState(false);
    const [redirectUserToHome, setRedirectUserToHome] = useState(false);

    const maxUsernamePasswordLen = 30;

    // 'e' is is the automatically create event handler object.
    const handleSubmit = async (e) => {
        e.preventDefault()
        
        const formData = new FormData(e.target);
        const userLogInData = {
            username: formData.get('_user'),
            userPassword: formData.get('_pass')
        };

        try {
            const user = await attemptLogIn(userLogInData);
            login(user);
            setRedirectUserToHome(true);
        } catch (err) {
            setShowErrorMessage(true);
        }


    };

    if (redirectUserToHome) {
        return (<Navigate to="/" />)
    }

    return (
        <div className="container">
            <div className="row mb-5 justify-content-center w-80">
                <div className="col-md-5">

                        <h2>Login</h2>
                        <form onSubmit={handleSubmit}>
                            <div className="row mt-5 mb-5 justify-content-center">
                                <label>Username: </label>
                                <input 
                                    name='_user'
                                    className="form-control"
                                    type="text"
                                    maxLength={maxUsernamePasswordLen}
                                />
                            </div>

                            <div className="row mb-5 justify-content-center">
                                <label>Password: </label>
                                <input 
                                    name='_pass'
                                    className="form-control"
                                    type="text"
                                    maxLength={maxUsernamePasswordLen}
                                />
                            </div>

                            <div className="row mb-5 justify-content-center">
                                {showErrorMessage && (
                                    <div className="alert alert-warning mt-2" role="alert">Error: Invalid Login Info.</div>
                                )}
                            </div>

                            <div className="row mb-5 justify-content-center">
                                <button type="submit" className="btn btn-primary">
                                    Log In
                                </button>
                            </div>
                        </form>
                </div>
            </div>
        </div>
    )



}

export default LogIn;
