// Have to create different file for context as it is a non-react component. Exporting a react and non-react component causes
// issues with vite and fast refresh (that what it says in the terminal) so I guess this is what I have to do.

import { createContext } from "react";

const UserContext = createContext();

export default UserContext;

