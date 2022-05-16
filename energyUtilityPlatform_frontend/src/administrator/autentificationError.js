import React, {Component} from "react";
import {withRouter} from "react-router-dom";
import sensorService from "../administrator/sensorService";
import '../index.css'
import ClientService from "../administrator/clientService";
import AdministratorService from "../administrator/administratorService"
import SensorService from "../administrator/sensorService";
import administratorService from "../administrator/administratorService";
class AutentificationErrorComponent extends Component {

    render() {
        return (
            <div>
                <h3 className="text-center">Autentification error!</h3>

            </div>
        )};
}

export default withRouter(AutentificationErrorComponent)