import axios from 'axios';

const SENSOR_API_BASE_URL = "http://localhost:8080/sensor";

class SensorService {

    getSensors(){
        return axios.get(SENSOR_API_BASE_URL);
    }

    createSensor(sensor,deviceId){
        return axios.post(SENSOR_API_BASE_URL+ '/'  + deviceId, sensor);
    }

    getSensorById(sensorId){
        return axios.get(SENSOR_API_BASE_URL + '/' + sensorId);
    }

    updateSensor(sensor,sensorId){
        return axios.put(SENSOR_API_BASE_URL + '/' + sensorId, sensor);
    }

    deleteSensor(sensorId){
        return axios.delete(SENSOR_API_BASE_URL + '/' + sensorId);
    }
}

export default new SensorService()