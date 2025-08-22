import { BrowserRouter, Routes, Route} from 'react-router-dom';
import { useState } from 'react'
import { UserProvider } from './contexts/UserContext';
import { ProtectedRoute } from './contexts/ProtectedRoute';
import UserList from './components/UserList';
import Home from './components/Home';
import LogIn from './components/LogIn';
import NaviBar from './components/Navibar';
import ExplorePolls from './components/ExplorePolls';
import ViewPoll from './components/ViewPoll';
import PollVoteResult from './components/PollVoteResult';
import SignUp from './components/SignUp';
import CreatePoll from './components/CreatePoll';

function App() {
  return (
    <>
      <UserProvider>
        <BrowserRouter>
          <NaviBar />
          <div id="routerTarget">
            <Routes>
              <Route path="/" element={<Home />}/>
              <Route path="/logIn" element={<LogIn />}/>
              <Route path="/signUp" element={<SignUp />}/>
              <Route path="/userList" element={<ProtectedRoute><UserList /></ProtectedRoute>}/>
              <Route path="/explorePolls" element={<ProtectedRoute><ExplorePolls /></ProtectedRoute>}/>
              <Route path="/explorePolls/:id" element={<ProtectedRoute><ViewPoll /></ProtectedRoute>}/>
              <Route path="/pollVoteResult/:id" element={<ProtectedRoute><PollVoteResult /></ProtectedRoute>}/>
              <Route path="/createPoll" element={<ProtectedRoute><CreatePoll /></ProtectedRoute>}/>
              <Route path="*" element={<Home />}/>
              <Route/>
            </Routes>
          </div>
        </BrowserRouter>
      </UserProvider>
    </>
  )
}

export default App
