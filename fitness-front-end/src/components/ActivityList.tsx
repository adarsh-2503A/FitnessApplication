import { Card, CardContent, CardMedia, Grid, Typography } from '@mui/material';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router';
import { getActivities } from '../Api';
import type { Activity } from '../types';

export const getActivityImage = (type?: string): string => {
  const images: Record<string, string> = {
    RUNNING: 'https://images.unsplash.com/photo-1528629297340-d1d466945dc5?auto=format&fit=crop&w=500&q=60',
    WALKING: 'https://images.unsplash.com/photo-1551632811-561732d1e306?auto=format&fit=crop&w=500&q=60',
    CYCLING: 'https://images.unsplash.com/photo-1517649763962-0c623066013b?auto=format&fit=crop&w=500&q=60',
    YOGA: 'https://images.unsplash.com/photo-1544367567-0f2fcb009e0b?auto=format&fit=crop&w=500&q=60',
    CARDIO: 'https://images.unsplash.com/photo-1534438327276-14e5300c3a48?auto=format&fit=crop&w=500&q=60'
  };
  return type && images[type.toUpperCase()] 
    ? images[type.toUpperCase()] 
    : 'https://images.unsplash.com/photo-1517836357463-d25dfeac3438?auto=format&fit=crop&w=500&q=60';
};

interface ActivityListProps {
  refreshTrigger?: number;
}

const ActivityList: React.FC<ActivityListProps> = ({ refreshTrigger }) => {
  const [activities, setActivities] = useState<Activity[]>([]);
  const navigate = useNavigate();

  const fetchActivities = async () => {
    try {
      const response = await getActivities();
      setActivities(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchActivities();
  }, [refreshTrigger]);

  return (
    <Grid container spacing={3} p={2}>
      {activities.map((activity) => (
        <Grid item xs={12} sm={6} md={4} key={activity.id}>
          <Card 
            sx={{
              cursor: 'pointer', 
              transition: 'transform 0.2s',
              '&:hover': { transform: 'scale(1.02)' }
            }}
            onClick={() => navigate(`/activities/${activity.id}`)}
          >
            <CardMedia
              component="img"
              height="200"
              image={getActivityImage(activity.activityType)}
              alt={activity.activityType}
            />
            <CardContent>
              <Typography variant='h6' sx={{ fontWeight: 'bold' }}>
                {activity.activityType}
              </Typography>
              <Typography color="text.secondary">
                Duration: {activity.duration} mins
              </Typography>
              <Typography color="text.secondary">
                Calories: {activity.caloriesBurned} kcal
              </Typography>
            </CardContent>
          </Card>
        </Grid>
      ))}
    </Grid>
  );
};

export default ActivityList;