import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ApiService from '../../service/ApiService';
import Pagination from '../Pagination/Pagination'
import RoomResult from '../Room/RoomResult';

const ManageRoomPage = () => {
    const [rooms, setRooms] = useState([]);
    const [filteredRooms, setFilteredRooms] = useState([]);
    const [roomTypes, setRoomTypes] = useState([]);
    const [selectedRoomType, setSelectedRoomType] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [roomsPerPage] = useState(5);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchRooms = async () => {
          try {
            const response = await ApiService.getAllRooms();
            const allRooms = response.roomList;
            setRooms(allRooms);
            setFilteredRooms(allRooms);
          } catch (error) {
            console.error('Error fetching rooms:', error.message);
          }
        };
    
        const fetchRoomTypes = async () => {
          try {
            const types = await ApiService.getRoomTypes();
            setRoomTypes(types);
          } catch (error) {
            console.error('Error fetching room types:', error.message);
          }
        };
    
        fetchRooms();
        fetchRoomTypes();
      }, []);
    
      const handleRoomTypeChange = (e) => {
        setSelectedRoomType(e.target.value);
        filterRooms(e.target.value);
      };
    
      const filterRooms = (type) => {
        if (type === '') {
          setFilteredRooms(rooms);
        } else {
          const filtered = rooms.filter((room) => room.roomType === type);
          setFilteredRooms(filtered);
        }
        setCurrentPage(1);
      };
    
      const indexOfLastRoom = currentPage * roomsPerPage;
      const indexOfFirstRoom = indexOfLastRoom - roomsPerPage;
      const currentRooms = filteredRooms.slice(indexOfFirstRoom, indexOfLastRoom);
    
      const paginate = (pageNumber) => setCurrentPage(pageNumber);
};

export default ManageRoomPage;