import axios from "axios";
import type { Activity } from "./components/ActivityForm";

const API_URL = 'http://localhost:8765/api';

const api = axios.create({
    baseURL:API_URL
});

api.interceptors.request.use((config) => {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
    }

    if (userId) {
        config.headers['X-User-ID'] = userId;
    }
    return config;
}
);


export const getActivities = () => api.get('/activities');
export const addActivity = (activity:Activity) => api.post('/activities', activity);
export const getActivityDetail = (id:string) => api.get(`/recommendations/activity/${id}`);