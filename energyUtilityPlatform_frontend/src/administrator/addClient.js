import React, { Component } from 'react'
import clientService from "./clientService";
import {withRouter} from "react-router-dom";

class AddClientComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            id: this.props.match.params.id,
            name: '',
            birthDate: '',
            address: '',
            username: '',
            password: ''
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeBirthDateHandler = this.changeBirthDateHandler.bind(this);
        this.changeAddressHandler = this.changeAddressHandler.bind(this);
        this.changeUsernameHandler = this.changeUsernameHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.saveClient = this.saveClient.bind(this);
    }

    componentDidMount(){

    }
    saveClient = (e) => {
        e.preventDefault();
        let client = {name: this.state.name, birthDate: this.state.birthDate, address: this.state.address,
            username: this.state.username, password: this.state.password};
        clientService.createClient(client).then(res => {
            this.props.history.push('/administrator');
        });
    }

    changeNameHandler= (event) => {
        this.setState({name: event.target.value});
    }

    changeBirthDateHandler= (event) => {
        this.setState({birthDate: event.target.value});
    }

    changeAddressHandler= (event) => {
        this.setState({address: event.target.value});
    }

    changeUsernameHandler= (event) => {
        this.setState({username: event.target.value});
    }
    changePasswordHandler= (event) => {
        this.setState({password: event.target.value});
    }
    cancel(){
        this.props.history.push('/administrator');
    }

    getTitle(){
        return <h3 className="text-center">Add Client</h3>
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
                                        <label> Name: </label>
                                        <input placeholder="Name" name="name" className="form-control"
                                               value={this.state.name} onChange={this.changeNameHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Birth Date: </label>
                                        <input placeholder="Birth Date" name="birthDate" className="form-control"
                                               value={this.state.birthDate} onChange={this.changeBirthDateHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Address: </label>
                                        <input placeholder="Address" name="address" className="form-control"
                                               value={this.state.address} onChange={this.changeAddressHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Username: </label>
                                        <input placeholder="Username" name="username" className="form-control"
                                               value={this.state.username} onChange={this.changeUsernameHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Password: </label>
                                        <input placeholder="Password" name="password" className="form-control"
                                               value={this.state.password} onChange={this.changePasswordHandler}/>
                                    </div>

                                    <button className="btn btn-success" onClick={this.saveClient}>Save</button>
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

export default withRouter(AddClientComponent)