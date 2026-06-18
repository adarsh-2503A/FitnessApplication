import { Box, Button, FormControl, InputLabel, MenuItem, Select, TextField, Typography, IconButton } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutlineOutlined';
import React, { useState } from 'react';
import { addActivity } from '../Api';
import type { Activity } from '../types';

interface ActivityFormProps {
  onActivityAdded: () => void;
}

interface MetricInput {
  key: string;
  value: string;
}

const ActivityForm: React.FC<ActivityFormProps> = ({ onActivityAdded }) => {
  const [activity, setActivity] = useState<Activity>({
    activityType: "RUNNING", 
    duration: '', 
    caloriesBurned: ''
  });

  const [metrics, setMetrics] = useState<MetricInput[]>([]);

  const handleAddMetric = () => {
    setMetrics([...metrics, { key: '', value: '' }]);
  };

  const handleMetricChange = (index: number, field: keyof MetricInput, val: string) => {
    const updatedMetrics = [...metrics];
    updatedMetrics[index][field] = val;
    setMetrics(updatedMetrics);
  };

  const handleRemoveMetric = (index: number) => {
    const updatedMetrics = metrics.filter((_, i) => i !== index);
    setMetrics(updatedMetrics);
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    const additionalMetricsObj = metrics.reduce((acc: Record<string, any>, curr) => {
      if (curr.key.trim() !== '') {
        acc[curr.key] = isNaN(Number(curr.value)) ? curr.value : Number(curr.value);
      }
      return acc;
    }, {});

    const payload: Activity = {
      ...activity,
      duration: Number(activity.duration),
      caloriesBurned: Number(activity.caloriesBurned),
      additionalMetrics: additionalMetricsObj
    };

    try {
      await addActivity(payload);
      onActivityAdded();
      setActivity({ activityType: "RUNNING", duration: '', caloriesBurned: '' });
      setMetrics([]);
    } catch (error) {
      console.error(error);
    }
  };
    
  return (
    <Box component="form" onSubmit={handleSubmit} sx={{ mb: 4, p: 3, boxShadow: 3, borderRadius: 2, bgcolor: 'background.paper' }}>
      <Typography variant="h5" sx={{ mb: 3, fontWeight: 'bold' }}>Log New Activity</Typography>
      
      <FormControl fullWidth sx={{ mb: 3 }}>
        <InputLabel>Activity Type</InputLabel>
        <Select
          value={activity.activityType}
          label="Activity Type"
          onChange={(e) => setActivity({ ...activity, activityType: e.target.value as string })}
        >
          <MenuItem value="RUNNING">Running</MenuItem>
          <MenuItem value="WALKING">Walking</MenuItem>
          <MenuItem value="CYCLING">Cycling</MenuItem>
          <MenuItem value="YOGA">Yoga</MenuItem>
          <MenuItem value="CARDIO">Cardio</MenuItem>
        </Select>
      </FormControl>

      <Box sx={{ display: 'flex', gap: 2, mb: 3 }}>
        <TextField 
          fullWidth
          label="Duration (Minutes)"
          type="number"
          required
          value={activity.duration}
          onChange={(e) => setActivity({ ...activity, duration: e.target.value })}
        />
        <TextField 
          fullWidth
          label="Calories Burned"
          type="number"
          required
          value={activity.caloriesBurned}
          onChange={(e) => setActivity({ ...activity, caloriesBurned: e.target.value })}
        />
      </Box>

      <Box sx={{ mb: 3, p: 2, border: '1px solid #e0e0e0', borderRadius: 1 }}>
        <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
          <Typography variant="subtitle1" fontWeight="bold">Additional Parameters</Typography>
          <Button startIcon={<AddCircleOutlineIcon />} onClick={handleAddMetric} size="small">
            Add Field
          </Button>
        </Box>
        
        {metrics.map((metric, index) => (
          <Box key={index} sx={{ display: 'flex', gap: 2, mb: 2, alignItems: 'center' }}>
            <TextField
              label="Parameter Name"
              placeholder="e.g., stepsTaken"
              size="small"
              fullWidth
              value={metric.key}
              onChange={(e) => handleMetricChange(index, 'key', e.target.value)}
            />
            <TextField
              label="Value"
              placeholder="e.g., 6500"
              size="small"
              fullWidth
              value={metric.value}
              onChange={(e) => handleMetricChange(index, 'value', e.target.value)}
            />
            <IconButton color="error" onClick={() => handleRemoveMetric(index)}>
              <DeleteIcon />
            </IconButton>
          </Box>
        ))}
        {metrics.length === 0 && (
          <Typography variant="body2" color="text.secondary">No additional parameters added.</Typography>
        )}
      </Box>

      <Button type="submit" variant="contained" size="large" fullWidth>
        Add Activity
      </Button>
    </Box>
  );
};

export default ActivityForm;