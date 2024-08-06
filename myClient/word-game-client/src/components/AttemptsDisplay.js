import React from 'react';
import { Typography, Box } from '@mui/material';

const AttemptsDisplay = ({ attempts }) => {
  return (
    <Box mt={2}>
      <Typography variant="body1">Attempts: {attempts}</Typography>
    </Box>
  );
};

export default AttemptsDisplay;
