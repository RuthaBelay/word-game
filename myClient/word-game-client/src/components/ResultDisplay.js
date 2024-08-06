import React from 'react';
import { Typography, Box, Tooltip, IconButton } from '@mui/material';
import InfoIcon from '@mui/icons-material/Info';

const ResultDisplay = ({ result }) => {
  const getBackgroundColor = (char) => {
    switch (char) {
      case 'G': return '#4caf50'; // Green
      case 'Y': return '#ffeb3b'; // Yellow
      case 'B': return '#757575'; // Grey/Black
      default: return 'transparent';
    }
  };

  return (
    <Box mt={2} position="relative" >
        <Tooltip title="G = Correct position, Y = Incorrect position, B = Not in word">

        <IconButton
          sx={{
            position: 'fxed',
            top: 26,
            left: 55,
            zIndex: 1,
            // backgroundColor:"blue", 
            // border:"solid"      
            }}
        >
          <InfoIcon />
        </IconButton>
      </Tooltip>
     
     
      <Typography variant="h6">
        Result:
        <Box display="flex" mt={1}>
          {result.split('').map((char, index) => (
            <Box
              key={index}
              sx={{
                width: 40,
                height: 40,
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                backgroundColor: getBackgroundColor(char),
                color: char === 'B' ? '#fff' : '#000', // Text color adjustment for better visibility
                borderRadius: 1,
                marginRight: 1
              }}
            >
              {char}
            </Box>
          ))}
        </Box>
      </Typography>
    </Box>
  );
};

export default ResultDisplay;
