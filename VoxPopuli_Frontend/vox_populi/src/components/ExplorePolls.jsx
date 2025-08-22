import { useState, useEffect } from 'react';
import { getAllPollOverviews } from '../scripts/apicalls';
import { Link } from 'react-router';
import Loading from './Loading';
import ErrorMessage from './Error';


function ExplorePolls() {
    const [polls, setPolls] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);


    useEffect(() => {
        const fetchPolls = async () => {
            try {
                setLoading(true);
                const data = await getAllPollOverviews();
                setPolls(data);
            } catch (err) {
                setError(err);
            } finally {
                setLoading(false);
            }
        }
     
        fetchPolls();
    }, []); // empty array, only run effect on first load

    if (loading) {
        return Loading();
    }

    if (error) {
        return ErrorMessage(error);
    }

    return (
        <>
            <div className='container'>
                    {polls.map((poll) => (
                            <div className='card mt-4 ' key={poll.pollId}>
                                <div className='card-body shadow-sm'>
                                    <h3 className='card-title'>{poll.pollTitle} </h3>
                                    <h5 className='card-subtitle mb-2 text-muted'>{poll.pollDescription}</h5>
                                    <h6><small className='text-muted'> By: {poll.pollAuthorUsername}</small></h6>
                                    <h6 className='card-text'><small className='text-muted'>Votes: {poll.votesOnPoll}</small></h6>
                                    <Link type="button" className='btn btn-primary view-poll-button' to={`/explorePolls/${poll.pollId}`}>View Poll</Link>
                                </div>
                            </div>
                        ))}
            </div>
        </>
    )
}

export default ExplorePolls;