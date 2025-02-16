import React, {useState, useEffect} from "react";
import ApiService from '../../Service/ApiService';
import Pagination from '../Pagination/Pagination';
import RoomResult from '../Room/RoomResult';
import RoomSearch from '../Room/RoomSearch';
import Footer from '../Footer/Footer';


const AllRoomsPage = () => {
    const [rooms, setRooms] = useState([]);
    const[filteredRooms, setFilteredRooms] = useState([]);
    const [roomTypes, setRoomTypes] = useState([]);
    const [selectedRoomType, setSelectedRoomType] = useState('');
    const [currentPage, setCurrentPage] = useState(1);
    const [roomsPerPage] = useState(5);

    //handle search results
    const handleSearchResult = (result) => {
        setRooms(result);
        setFilteredRooms(result);
    };

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
                const parsedTypes = types.map(type => JSON.parse(type)); 
                setRoomTypes(parsedTypes);
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

    //Pagination
    const indexOfLastRoom = currentPage * roomsPerPage;
    const indexOfFirstRoom = indexOfLastRoom - roomsPerPage;
    const currentRooms = filteredRooms.slice(indexOfFirstRoom, indexOfLastRoom);

    //change page
    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div className="all-rooms">
            <h2>All Rooms</h2>
            <div className="all-room-filter-div">
                <label>Filter by Room Type:</label>
                <select value={selectedRoomType} onChange={handleRoomTypeChange}>
                    <option value="">All</option>
                    {roomTypes.map((type,index) => (
                        <option key={index} value={type.roomType}>
                            {type.roomType}
                        </option>
                    ))}
                </select>
            </div>

            <RoomSearch handleSearchResult={handleSearchResult} />
            <RoomResult roomSearchResults={currentRooms} />

            <Pagination
                roomsPerPage={roomsPerPage}
                totalRooms={filteredRooms.length}
                currentPage={currentPage}
                paginate={paginate}
            />

            <Footer />
        </div>

    );

};

export default AllRoomsPage;