import { useState, useEffect } from 'react';
import { getAllPollOverviews, getNumberOfPolls } from '../scripts/apicalls';
import { Link } from 'react-router';
import Loading from './Loading';
import ErrorMessage from './Error';


function ExplorePolls() {
    const [polls, setPolls] = useState([]); // Not all polls, but just the polls on the current page (max 10)
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [numberOfPolls, setNumberOfPolls] = useState(0);
    const [pageNumber, setPageNumber] = useState(0); // Page 0 is the first page, 1 represents the second page.

    const [renderPrevPageButton, setRenderPrevPageButton] = useState(true);
    const [renderNextPageButton, setRenderNextPageButton] = useState(true);


    useEffect(() => {
        const fetchPolls = async () => {
            try {
                setLoading(true);
                const polls = await getAllPollOverviews(pageNumber);

                setPolls(polls);
            } catch (err) {
                setError(err);
            } finally {
                setLoading(false);
            }
        }

        const doRenderPageButtons = async () => {
            const numPolls = await getNumberOfPolls();
            setNumberOfPolls(numPolls);

            if (pageNumber <= 0) {
                setRenderPrevPageButton(false);
            } else {
                setRenderPrevPageButton(true);
            }

            if (pageNumber >= Math.ceil(numPolls / 10) - 1) {
                setRenderNextPageButton(false);
            } else {
                setRenderNextPageButton(true);
            }
        }
        
        fetchPolls();
        doRenderPageButtons();
    }, [pageNumber]); // empty array, only run effect on first load

    const prevPage = () => {
        let newPageNumber = pageNumber - 1;

        if (pageNumber > 0) {
            setPageNumber(newPageNumber);
        }
    }

     const nextPage = () => {
        let newPageNumber = pageNumber + 1;

        if (pageNumber < Math.ceil(numberOfPolls / 10) - 1) {
            setPageNumber(newPageNumber);
        }
    }

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
                            <div className='card mt-3 mb-3' key={poll.pollId}>
                                <div className='card-body shadow-sm'>
                                    <h3 className='card-title'>{poll.pollTitle} </h3>
                                    <h5 className='card-subtitle mb-2 text-muted'>{poll.pollDescription}</h5>
                                    <h6><small className='text-muted'> By: {poll.pollAuthorUsername}</small></h6>
                                    <h6 className='card-text'><small className='text-muted'>Votes: {poll.votesOnPoll}</small></h6>
                                    <Link type="button" className='btn btn-primary view-poll-button' to={`/explorePolls/${poll.pollId}`}>View Poll</Link>
                                </div>
                            </div>
                        ))}

                    {/* Pagination UI*/}

                    <div className='d-flex align-items-center justify-content-center gap-2 mb-2'>
                        {renderPrevPageButton && (
                        <button 
                            className='btn btn-secondary'
                            onClick={prevPage}
                        >
                            ←
                        </button>

                        )}
                        <span className='border px-3 py-2 rounded'>{pageNumber + 1}</span>

                        {renderNextPageButton && (
                            <button 
                                className='btn btn-secondary'
                                onClick={nextPage}
                            >
                                →
                            </button>
                        )}
                    </div>
            </div>
        </>
    )
}

export default ExplorePolls;