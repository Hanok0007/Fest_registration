import React, { useState } from 'react';
import axios from 'axios';

const Registration = () => {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        contactNumber: '',
        eventId: ''
    });

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/participants/register', formData);
            console.log(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" placeholder="Name" onChange={(e) => setFormData({ ...formData, name: e.target.value })} />
            <input type="email" placeholder="Email" onChange={(e) => setFormData({ ...formData, email: e.target.value })} />
            <input type="text" placeholder="Contact Number" onChange={(e) => setFormData({ ...formData, contactNumber: e.target.value })} />
            <input type="text" placeholder="Event ID" onChange={(e) => setFormData({ ...formData, eventId: e.target.value })} />
            <button type="submit">Register</button>
        </form>
    );
};

export default Registration;