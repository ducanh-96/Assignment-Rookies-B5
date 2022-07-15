import "bootstrap/dist/css/bootstrap.css";

import "./App.css";

import "primereact/resources/themes/lara-light-teal/theme.css"; //theme
import "primereact/resources/primereact.min.css"; //core css
import "primeicons/primeicons.css"; //icons

import { Container } from "react-bootstrap";
import AdminPage from "./Components/Admin/AdminPage";
import Login from "./Components/Login"
import Register from "./Components/Register"

function App() {
  return (
    <Container className="text-center" style={{backgroundColor:"white"}}>
      <AdminPage/>
      <Login/>
      <Register/>
    </Container>
  );
}

export default App;
