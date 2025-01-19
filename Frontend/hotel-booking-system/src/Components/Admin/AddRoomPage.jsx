import React, {useState, useEffect} from "react";
import { useNavigate } from "react-router-dom";
import ApiService from "../../Service/ApiService";

const AddRoomPage = () => {
    const navigatec = useNavigate();
    const [roomDetails, setRoomDetails] = useState({
        roomPhotoUrl: '',
        roomType: '',
        roomPrice: '',
        roomDescription: '',
    });
    const [file, setFile] = useState(null);
    const [preview, setPreview] = useState(null);
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');
    const [roomTypes, setRoomTypes] = useState([]);
    const [newRoomType, setNewRoomType] = useState([]);
   
    useEffect(() => {
        const fetchRoomTypes = async () => {
            try {
                const types = await ApiService.getRoomTypes();
                setRoomTypes(types);
            } catch (error) {
                console.error('Error fetching room types:',error.message);
            }
        };
        fetchRoomTypes();
    }, []);
    const handleChange = (e) => {
        const { name, value } = e.target;
        setRoomDetails(prevState => ({
            ...prevState,
            [name]: value,
        }));

        const handleRoomTypeChange = (e) => {
            if (e.target.value === 'new') {
                setNewRoomType(true);
                setRoomDetails(prevState => ({ ...prevState, roomType: '' }));
            } else {
                setNewRoomType(false);
                setRoomDetails(prevState => ({ ...prevState, roomType: e.target.value }));
            }
        };
}