import { Link } from "react-router";

function SuccessMessage(error) {
    return (
            <div className="container mt-4">
                <div className="alert alert-success" role="alert">
                    <h4 className="alert-heading">Success!</h4>
                    <p>Action Successful: {error.toString()}</p>
                    <Link type="button" className='btn btn-success view-poll-button' to={'/'}>Go Home</Link>
                </div>
            </div>
        );
}

export default SuccessMessage