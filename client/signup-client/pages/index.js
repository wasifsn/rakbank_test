import React from 'react';
import Link from 'next/link';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
const Home = () => {
  return (
    <Container>
      <Typography variant="h4" gutterBottom>
        Welcome to the App
      </Typography>
      <Link href="/signup">Go to Signup</Link>
    </Container>
  );
};

export default Home;
