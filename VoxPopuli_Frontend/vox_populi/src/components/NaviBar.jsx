import { NavLink } from 'react-router-dom';
import { useContext } from 'react';
import UserContext from '../contexts/CreateUserContext';

function NaviBar() {
    const { isLoggedIn, logout, userData } = useContext(UserContext);


    return (
        <nav className='navbar navbar-inverse navbar-dark bg-dark'>
            <div className='container-fluid'>
                <div className='navbar-header'>
                    <a className='navbar-brand' href='/' style={{ fontSize:"150%", justifyContent:"left"}}><strong>Vox Populi</strong></a>
                </div>
                <header className='d-flex justify-content-center py-3'>
                    <ul className='nav nav-pills'>
                        <li className='nav-item'>
                                 <NavLink className='nav-link' to="/">Home</NavLink>
                            </li>
                    {isLoggedIn ? (
                        <> 
                            {userData.accessLevel === 1 && (
                                <li> 
                                    <NavLink className='nav-link' to="/userList">Users</NavLink>
                                </li>
                            )}
                            <li> 
                                <NavLink className='nav-link' to="/explorePolls">Explore</NavLink>
                            </li>
                            <li> 
                                <NavLink className='nav-link' to="/createPoll">Create</NavLink>
                            </li>
                            <li className='nav-item'>
                                 <button className='btn btn-link nav-link' onClick={logout}>Log Out</button>
                            </li>
                            <li className='nav-item'>
                                 <span className='nav-link text-light'>{userData.username}</span>
                            </li>
                        </>  
                    ) : (
                        <> 
                            <li className='nav-item'>
                                 <NavLink className='nav-link' to="/logIn">Log In</NavLink>
                            </li>
                            <li className='nav-item'>
                                 <NavLink className='nav-link' to="/signUp">Sign Up</NavLink>
                            </li>
                        </>  
                    )}
                    </ul>
                </header>
            </div>
        </nav>
    )
}

export default NaviBar;