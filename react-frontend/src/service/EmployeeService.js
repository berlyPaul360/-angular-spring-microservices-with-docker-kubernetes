import axios from 'axios';

const EMPLOYEE_SERVICE_BASE_URL = "http://localhost:9191/employee-service/api/employees";  // Added http:// at the beginning

const EMPLOYEE_ID = 2;

class EmployeeService {
    getEmployee() {
        return axios.get(`${EMPLOYEE_SERVICE_BASE_URL}/${EMPLOYEE_ID}`);  // Used template string for cleaner code
    }
}

export default new EmployeeService();
