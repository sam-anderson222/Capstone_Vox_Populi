

export async function getAllPollOverviews() {
    // call the api
    const response = await fetch('http://localhost:8080/api/polls/overviews');

    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`)
    }

    const data = await response.json();
    return data;
};

export async function getPoll(id) {
    // call the api
    const response = await fetch(`http://localhost:8080/api/polls/${id}`);

    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`)
    }

    const data = await response.json();
    return data;
};

export async function getAllUsers() {
    // call the api
    const response = await fetch('http://localhost:8080/api/users');

    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`)
    }

    const data = await response.json();
    return data;
};

export async function attemptLogIn(logInData) {
    const response = await fetch('http://localhost:8080/api/users/logIn', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(logInData)
    });

    if (response.ok) {
        const data = await response.json();
        return data;
    } else if (response.status == 401) {
        throw new Error('Invalid Login Details!');
    } else {
        throw new Error(`HTTP error! Status: ${response.status}`)
    }
};

export async function attemptSignUp(signUpData) {
    const response = await fetch('http://localhost:8080/api/users/signUp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(signUpData)
    });

    if (response.ok) {
        const data = await response.json();
        return data;
    } else if (response.status == 409) {
        throw new Error('Could Not Register Account.');
    } else {
        throw new Error(`HTTP error! Status: ${response.status}`)
    }
};

export async function voteOnPoll(pollVote) {
    const response = await fetch('http://localhost:8080/api/pollVotes/vote', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(pollVote)
    });

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    const data = await response.json();
    return data;
};

export async function getUserVoteOnPoll(pollId, user) {
    const response = await fetch(`http://localhost:8080/api/pollVotes/hasVoted/${pollId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user)
    });

    if (response.status == 204) {
        return null; // Do nothing, as 204 means user hasn't voted on this poll yet.
    } else if (response.ok) {
        const data = await response.json();
        return data;
    } else {
        throw new Error(`HTTP error! Status: ${response.status}`)
    }
};

export async function getPollResults(id) {
    // call the api
    const response = await fetch(`http://localhost:8080/api/pollVotes/${id}`);

    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`)
    }

    const data = await response.json();
    return data;
};

export async function createNewPoll(newPollData) {
    const response = await fetch('http://localhost:8080/api/polls', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:JSON.stringify(newPollData)
    });

    if (response.ok) {
        const data = await response.json();
        return data;
    } else if (response.status == 409) {
        throw new Error('Could not create poll.');
    } else {
        throw new Error(`HTTP error! Status: ${response.status}`)
    }
};