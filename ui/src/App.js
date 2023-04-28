import { useContext } from "react";

import Welcome from "./pages/welcome";
import AppContext from "./context/AppContext";

function App() {
    const {state, dispatch} = useContext(AppContext);

    return (
        <div>
            <Welcome/>
        </div>
    );
}

export default App;
