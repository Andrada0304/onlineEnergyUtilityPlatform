import React, { Component } from 'react'
import deviceService from "./deviceService";
import {withRouter} from "react-router-dom";
import clientService from "./clientService";
import sensorService from "./sensorService";

class AddSensorComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            sensorDescription: '',
            maximumValue: ''
        }
        this.changeSensorDescriptionHandler = this.changeSensorDescriptionHandler.bind(this);
        this.changeMaximumValueConsumptionHandler = this.changeMaximumValueHandler.bind(this);
        this.saveSensor = this.saveSensor.bind(this);
    }

    componentDidMount(){
    }
    saveSensor = (e) => {
        e.preventDefault();
        let sensor = {sensorDescription: this.state.sensorDescription,
            maximumValue: this.state.maximumValue};
        console.log('sensor => ' + JSON.stringify(sensor))
        sensorService.createSensor(sensor,this.state.id).then(res => {
            this.props.history.push('/administrator');
        });
    }
    changeSensorDescriptionHandler= (event) => {
        this.setState({sensorDescription: event.target.value});
    }

    changeMaximumValueHandler= (event) => {
        this.setState({maximumValue: event.target.value});
    }
    cancel(){
        this.props.history.push('/administrator');
    }

    getTitle(){
        return <h3 className="text-center">Add Sensor</h3>
    }
    render() {
        return (
            <div>
                <br></br>
                <div className = "container">
                    <div className = "row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            {
                                this.getTitle()
                            }
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Sensor description: </label>
                                        <input placeholder="Sensor Description" name="sensorDescription" className="form-control"
                                               value={this.state.sensorDescription} onChange={this.changeSensorDescriptionHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Maximum value: </label>
                                        <input placeholder="MaximumValue" name="maximumValue" className="form-control"
                                               value={this.state.maximumValue} onChange={this.changeMaximumValueHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={this.saveSensor}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        )
    }
}

export default withRouter(AddSensorComponent)