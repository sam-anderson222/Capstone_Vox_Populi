// Re-directs user if they attempt to access a restricted page while not logged in.

import { Navigate } from "react-router";
import UserContext from "./CreateUserContext";
import { useContext } from "react";

export function ProtectedRoute({ children }) {
    const { isLoggedIn } = useContext(UserContext);


    if (!isLoggedIn) {
        return (<Navigate to='/'/>)
    }

    // if are logged in, render the page normally.
    return children
}