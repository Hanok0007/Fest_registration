import React, { useState, useEffect } from 'react';
import api from '../services/api';

function DataFetcher() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await api.get('/your-endpoint'); // Replace with your endpoint
        setData(response.data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div>
      <h2>Data from Backend</h2>
      <ul>
        {data.map((item) => (
          <li key={item.id}>{item.name}</li> // Adjust according to your data structure
        ))}
      </ul>
    </div>
  );
}

export default DataFetcher;