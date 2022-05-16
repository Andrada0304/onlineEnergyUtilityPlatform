import axios from 'axios';

const ADMINISTRATOR_API_BASE_URL = "http://localhost:8080/administrator";

class AdministratorService {

    getAdministrators(){
        return axios.get(ADMINISTRATOR_API_BASE_URL);
    }



}

export default new AdministratorService()