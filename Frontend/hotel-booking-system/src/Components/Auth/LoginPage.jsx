import React, { useState } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import ApiService from "../../Service/ApiService";

function LoginPage() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();
    const location = useLocation();

  const from = location.state?.from?.pathname || '/home';


    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!email || !password) {
            setError('Please fill in all fields.');
            setTimeout(() => setError(''), 5000);
            return;
        }

        
    };

    
}

export default LoginPage;