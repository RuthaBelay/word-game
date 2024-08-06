import React from 'react';
import { Typography, Button, Box } from '@mui/material';

const MessageDisplay = ({ message, handleNewGame, gameStatus }) => {
  return (
    gameStatus === 'finished' && (
      <Box mt={2} textAlign="center">
        <Typography variant="h6">{message}</Typography>
        <Button variant="contained" color="secondary" onClick={handleNewGame} sx={{ mt: 2 }}>
          Start New Game
        </Button>
      </Box>
    )
  );
};

export default MessageDisplay;
