import React from 'react';

import BackgroundImg from '../commons/images/img.jpg';

import {Button, Container, Jumbotron} from 'reactstrap';

const backgroundStyle = {
    backgroundPosition: 'center',
    backgroundSize: 'cover',
    backgroundRepeat: 'no-repeat',
    width: "100%",
    height: "1120px",
    backgroundImage: `url(${BackgroundImg})`
};
const textStyle = {color: 'black', textAlign:'center'};

class Home extends React.Component {


    render() {

        return (

            <div>
                <Jumbotron fluid style={backgroundStyle}>
                    <Container fluid>
                        <h1 className="display-3" style={textStyle}>Online Energy Utility Platform</h1>

                    </Container>
                </Jumbotron>

            </div>
        )
    };
}

export default Home
