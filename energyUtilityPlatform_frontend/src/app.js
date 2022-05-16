import React from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import NavigationBar from './navigation-bar'
import Home from './home/home';
import PersonContainer from './person/person-container'
import AdministratorContainerComponent from './administrator/administrator-container'
import AddClient from './administrator/addClient'
import AddDevice from './administrator/addDevice'
import AddSensor from './administrator/addSensor'
import EditSensor from './administrator/editSensor'
import ClientContainer from './administrator/client-container'
import AutentificationError from './administrator/autentificationError'
import EditClient from './administrator/editClient'
import Login from './login/login-container'
import EditDevice from './administrator/editDevice'
import ErrorPage from './commons/errorhandling/error-page';
import styles from './commons/styles/project-style.css';
import ClientContainerComponent from "./administrator/client-container";
import AutentificationeErrorComponent from "./administrator/autentificationError";

class App extends React.Component {


    render() {

        return (
            <div className={styles.back}>
                <Router>
                    <div>
                        <NavigationBar />
                        <Switch>

                            <Route
                                exact
                                path='/'
                                render={() => <Home/>}
                            />

                            <Route
                                exact
                                path='/administrator'
                                render={() => <AdministratorContainerComponent/>}
                            />
                            <Route
                                exact
                                path='/addClient'
                                render={() => <AddClient/>}
                            />
                            <Route
                                exact
                                path='/editClient/:id'
                                render={() => <EditClient/>}
                            />
                            <Route
                                exact
                                path='/editDevice/:id'
                                render={() => <EditDevice/>}
                            />
                            <Route
                                exact
                                path='/editSensor/:id'
                                render={() => <EditSensor/>}
                            />
                            <Route
                                exact
                                path='/addDevice/:id'
                                render={() => <AddDevice/>}
                            />
                            <Route
                                exact
                                path='/login'
                                render={() => <Login/>}
                            />
                            <Route
                                exact
                                path='/client'
                                render={() => <ClientContainer/>}
                            />
                            <Route
                                exact
                                path='/autentificationError'
                                render={() => <AutentificationError/>}
                            />
                            <Route
                                exact
                                path='/addSensor/:id'
                                render={() => <AddSensor/>}
                            />
                            {/*Error*/}
                            <Route
                                exact
                                path='/error'
                                render={() => <ErrorPage/>}
                            />

                            <Route render={() =><ErrorPage/>} />
                        </Switch>
                    </div>
                </Router>
            </div>
        )
    };
}

export default App
