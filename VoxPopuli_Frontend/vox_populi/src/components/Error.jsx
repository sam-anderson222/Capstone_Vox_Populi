import { Link } from "react-router";

function ErrorMessage(error) {
    return (
            <div className="container mt-4">
                <div className="alert alert-danger" role="alert">
                    <h4 className="alert-heading">Error!</h4>
                    <p>An error has occured: {error.toString()}</p>
                    <Link type="button" className='btn btn-danger view-poll-button' to={'/'}>Go Home</Link>
                </div>
            </div>
        );
}

export default ErrorMessage