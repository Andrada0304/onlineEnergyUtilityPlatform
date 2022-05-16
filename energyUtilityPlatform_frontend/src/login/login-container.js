import React, {Component} from "react";
import {withRouter} from "react-router-dom";
import sensorService from "../administrator/sensorService";
import '../index.css'
import ClientService from "../administrator/clientService";
import AdministratorService from "../administrator/administratorService"
import SensorService from "../administrator/sensorService";
import administratorService from "../administrator/administratorService";
import BackgroundImg from "../commons/images/login.jpg";
import {Jumbotron} from "reactstrap";


const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "1120px",
    backgroundImage: `url(${BackgroundImg})`
};

const textStyle = {color: 'white'};
class LoginContainerComponent extends Component {


    constructor(props) {
        super(props)

        this.state = {
            // step 2
            id: this.props.match.params.id,
            username: '',
            password: '',
            clients:[],
            administrators:[]
        }
        this.changeUsernameHandler = this.changeUsernameHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.login = this.login.bind(this);
    }
    componentDidMount(){
        ClientService.getClients().then((res) => {
            this.setState({ clients: res.data});
        });

        AdministratorService.getAdministrators().then((res) => {
            this.setState({ administrators: res.data});
        });
    }

    changeUsernameHandler= (event) => {
        this.setState({username: event.target.value});
    }
    changePasswordHandler= (event) => {
        this.setState({password: event.target.value});
    }
    login = (e) => {
        let k =0 ;
        this.state.clients.forEach((client) => {
            if(client.username == this.state.username && client.password == this.state.password){
                k=1;
                this.props.history.push(`/client`);
            }
        });

        this.state.administrators.forEach((administrator) => {
            if(administrator.username == this.state.username && administrator.password == this.state.password){
                k=1;
                this.props.history.push(`/administrator`);
            }
        });
        if(k==0){
            this.props.history.push(`/autentificationError`);
        }
    }
    render() {
        return (
            <div>
                <Jumbotron fluid style={backgroundStyle}>
                    <h3 className="text-center"  style={textStyle}>Login</h3>
                    <div className="col col-sm-6 offset-sm-3">
                        <label  style={textStyle}>Username:</label>
                        <input placeholder="Username" username = "username" className="form-control"
                               value={this.state.username} onChange={this.changeUsernameHandler}/>
                    </div>
                    <div className="col col-sm-6 offset-sm-3">
                        <label  style={textStyle}>Password:</label>
                        <input type = "password" placeholder="Password" password = "password" className="form-control"
                               value={this.state.password} onChange={this.changePasswordHandler}/>
                    </div>

                    <button className="btn-login" onClick={this.login}>Login</button>
                </Jumbotron>
            </div>
        )};
}

export default withRouter(LoginContainerComponent)