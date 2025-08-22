import { useContext } from "react";
import { Link } from "react-router";
import UserContext from "../contexts/CreateUserContext";

function Home() {
    const { isLoggedIn, userData } = useContext(UserContext);


    if (isLoggedIn) {
        return (
            <div className="container">
                <div className="row mt-3 justify-content-center w-80">
                    <div className="col-md-8">
                        {/* Explore Polls*/}
                        <div className="row justify-content-center">
                            <div className="card shadow-sm mb-3">
                                <div className="card-body home-screen-text">
                                    Welcome Back {userData.username}! Want To Explore More Polls?
                                </div>
                            </div>
                            <Link to='/explorePolls' className="btn btn-primary home-link-button text-center mt-4 shadow">
                                    Explore Polls
                            </Link>
                        </div>

                        {/* Or Text*/}
                        <div className="row">
                            <h4 className="text-center text-secondary mt-5 mb-5"><u>Or</u></h4>
                        </div>

                        {/* Create Polls*/}
                        <div className="row justify-content-center">
                            <div className="card shadow-sm mb-3">
                                <div className="card-body home-screen-text">
                                    Wanting To Create A Poll? Ask Away!
                                </div>
                            </div>
                            <Link to='/createPoll' type="button" className="btn btn-primary home-link-button text-center mt-4 shadow">
                                    Create Poll
                            </Link>
                        </div>

                    </div>
                </div>
            </div>
        )
    }

    return (
        <div className="container">
                <div className="row mt-3 justify-content-center w-80">
                    <div className="col-md-8">
                        {/* Explore Polls*/}
                        <div className="row justify-content-center">
                            <div className="card shadow-sm mb-3">
                                <div className="card-body home-screen-text">
                                    🔥 Settle The Debate on Life's Biggest Questions 🔥
                                </div>
                            </div>
                            <div className="card shadow-sm mb-3 w-50">
                                <div className="card-body home-screen-subtext">
                                    Chocolate or Vanilla?<br />
                                    Apple or Android?<br />
                                    Coke, Pepsi, or Dr.Pepper?<br />
                                    Xbox Vs. PlayStation Vs. Switch<br />
                                    Pineapple on Pizza, Yay or Nay?
                                </div>
                            </div>
                        </div>

                        {/* Or Text*/}
                        <div className="row">
                            <h4 className="text-center text-secondary mt-5 mb-5"><u>Or</u></h4>
                        </div>

                        {/* Create Polls*/}
                        <div className="row justify-content-center">
                            <div className="card shadow-sm mb-3">
                                <div className="card-body home-screen-text">
                                    Create Your Own Polls! Find Answers to Your Burning Questions!
                                </div>
                            </div>
                            <Link to='/signUp' type="button" className="btn btn-primary home-link-button text-center mt-4 shadow">
                                    Start Now!
                            </Link>
                        </div>

                    </div>
                </div>
            </div>
    )
}

export default Home;