import React, { Component } from 'react'
import deviceService from "./deviceService";
import {withRouter} from "react-router-dom";
import clientService from "./clientService";

class AddDeviceComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            description: '',
            clientId: '',
            address: '',
            maximumEnergyConsumption: '',
            avgEnergyConsumption: ''
        }
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.changeAddressHandler = this.changeAddressHandler.bind(this);
        this.changeMaximumEnergyConsumptionHandler = this.changeMaximumEnergyConsumptionHandler.bind(this);
        this.changeAvgEnergyConsumptionHandler = this.changeAvgEnergyConsumptionHandler.bind(this);
        this.saveDevice = this.saveDevice.bind(this);
    }

    componentDidMount(){
    }
    saveDevice = (e) => {
        e.preventDefault();
        let device = {description: this.state.description, address: this.state.address,
            maximumEnergyConsumption: this.state.maximumEnergyConsumption,avgEnergyConsumption: this.state.avgEnergyConsumption};

        console.log('device => ' + JSON.stringify(device))
        deviceService.createDevice(device,this.state.id).then(res => {
            this.props.history.push('/administrator');
        });
    }
    changeDescriptionHandler= (event) => {
        this.setState({description: event.target.value});
    }

    changeAddressHandler= (event) => {
        this.setState({address: event.target.value});
    }

    changeMaximumEnergyConsumptionHandler= (event) => {
        this.setState({maximumEnergyConsumption: event.target.value});
    }
    changeAvgEnergyConsumptionHandler= (event) => {
        this.setState({avgEnergyConsumption: event.target.value});
    }
    cancel(){
        this.props.history.push('/administrator');
    }

    getTitle(){
        return <h3 className="text-center">Add Device</h3>
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
                                        <label> Description: </label>
                                        <input placeholder="Description" name="description" className="form-control"
                                               value={this.state.description} onChange={this.changeDescriptionHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Address: </label>
                                        <input placeholder="Address" name="address" className="form-control"
                                               value={this.state.address} onChange={this.changeAddressHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Maximum energy consumption: </label>
                                        <input placeholder="MaximumEnergyConsumption" name="maximumEnergyConsumption" className="form-control"
                                               value={this.state.maximumEnergyConsumption} onChange={this.changeMaximumEnergyConsumptionHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Average energy consumption: </label>
                                        <input placeholder="AvgEnergyConsumption" name="avgEnergyConsumption" className="form-control"
                                               value={this.state.avgEnergyConsumption} onChange={this.changeAvgEnergyConsumptionHandler}/>
                                    </div>

                                    <button className="btn btn-success" onClick={this.saveDevice}>Save</button>
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

export default withRouter(AddDeviceComponent)