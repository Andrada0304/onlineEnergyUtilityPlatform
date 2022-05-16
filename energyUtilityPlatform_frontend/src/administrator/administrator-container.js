import React, { Component } from 'react'
//import EmployeeService from '../services/EmployeeService'
import ClientService from './clientService'
import DeviceService from './deviceService'
import SensorService from './sensorService'
import {withRouter} from "react-router-dom";

class AdministratorContainerComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            clients: [],
            devices: [],
            sensors: [],
        }
        this.addClient = this.addClient.bind(this);
        this.editClient = this.editClient.bind(this);
        this.deleteClient = this.deleteClient.bind(this);
        this.addDevice = this.addDevice.bind(this);
        this.editDevice = this.editDevice.bind(this);
        this.deleteDevice =this.deleteDevice.bind(this);
    }

    deleteClient(id){
        ClientService.deleteClient(id).then( res => {
            this.setState({clients: this.state.clients.filter(client => client.id !== id)});
        });
    }
    deleteDevice(id){
        DeviceService.deleteDevice(id).then( res => {
            this.setState({devices: this.state.devices.filter(device => device.id !== id)});
        });
    }

    deleteSensor(id){
        SensorService.deleteSensor(id).then( res => {
            this.setState({sensors: this.state.sensors.filter(sensor => sensor.id !== id)});
        });
    }


    editClient(id){
        this.props.history.push(`/editClient/${id}`);
    }
    editDevice(id){
        this.props.history.push(`/editDevice/${id}`);
    }
    editSensor(id){
        this.props.history.push(`/editSensor/${id}`);
    }
    addDevice(id){
        this.props.history.push(`/addDevice/${id}`);
    }

    addSensor(id){
        this.props.history.push(`/addSensor/${id}`);
    }

    componentDidMount(){
        ClientService.getClients().then((res) => {
            this.setState({ clients: res.data});
        });

        DeviceService.getDevices().then((res) => {
            this.setState({ devices: res.data});
        });
        SensorService.getSensors().then((res) => {
            this.setState({ sensors: res.data});
        });
    }

    addClient(){
        this.props.history.push('/addClient');
    }



    render() {
        return (
            <div>
                <h2 className="text-center">Admin's page</h2>
                <div className = "row">
                    <button className="btn btn-primary" onClick={this.addClient}> Add Client</button>
                </div>
                <br></br>
                <div className = "row">
                    <table className = "table table-striped table-bordered">

                        <thead>
                        <tr>
                            <th> Name</th>
                            <th> Birth Date</th>
                            <th> Address</th>
                            <th> Username</th>
                            <th> Password</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.clients.map(
                                client =>
                                    <tr key = {client.id}>
                                        <td> { client.name} </td>
                                        <td> { client.birthDate}</td>
                                        <td> { client.address}</td>
                                        <td> { client.username}</td>
                                        <td> { client.password}</td>
                                        <td>
                                            <button onClick={ () => this.editClient(client.id)} className="btn btn-info">Update </button>
                                            <button style={{marginLeft: "10px"}} onClick={ () => this.deleteClient(client.id)} className="btn btn-danger">Delete </button>
                                            <button onClick={ () => this.addDevice(client.id)} className="btn btn-info">Add Device </button>

                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>

                </div>
                <div className = "row">
                    <table className = "table table-striped table-bordered">

                        <thead>
                        <tr>
                            <th> Description</th>
                            <th> Address</th>
                            <th> Maximum Energy Consumption</th>
                            <th> Average Energy Consumption</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.devices.map(
                                device =>
                                    <tr key = {device.id}>
                                        <td> { device.description} </td>
                                        <td> { device.address}</td>
                                        <td> { device.maximumEnergyConsumption}</td>
                                        <td> { device.avgEnergyConsumption}</td>
                                        <td>
                                            <button onClick={ () => this.editDevice(device.id)} className="btn btn-info">Update </button>
                                            <button style={{marginLeft: "10px"}} onClick={ () => this.deleteDevice(device.id)} className="btn btn-danger">Delete </button>
                                            <button onClick={ () => this.addSensor(device.id)} className="btn btn-info">Add Sensor </button>

                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>

                </div>

                <div className = "row">
                    <table className = "table table-striped table-bordered">

                        <thead>
                        <tr>
                            <th> Sensor Description</th>
                            <th> Maximum Value</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.sensors.map(
                                sensor =>
                                    <tr key = {sensor.id}>
                                        <td> { sensor.sensorDescription} </td>
                                        <td> { sensor.maximumValue}</td>
                                        <button onClick={ () => this.editSensor(sensor.id)} className="btn btn-info">Update </button>
                                        <button style={{marginLeft: "10px"}} onClick={ () => this.deleteSensor(sensor.id)} className="btn btn-danger">Delete </button>

                                    </tr>
                            )
                        }
                        </tbody>
                    </table>

                </div>
            </div>
        )
    }
}

export default withRouter(AdministratorContainerComponent)