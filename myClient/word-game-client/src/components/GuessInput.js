import React from 'react';
import { TextField, Button } from '@mui/material';

const GuessInput = ({ guess, setGuess, handleGuess, gameStatus }) => {
  return (
    <div>
      <TextField
        label="Your Guess"
        variant="outlined"
        value={guess}
        onChange={(e) => setGuess(e.target.value)}
        fullWidth
        margin="normal"
        disabled={gameStatus === 'finished'}
      />
      <Button
        variant="contained"
        color="primary"
        onClick={handleGuess}
        disabled={gameStatus === 'finished'}
      >
        Submit Guess
      </Button>
    </div>
  );
};

export default GuessInput;
