import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import { getActivityDetail } from '../Api';
import { Box, Card, CardContent, CardMedia, Divider, Typography, Chip, Grid } from '@mui/material';
import { getActivityImage } from './ActivityList';
import type { Activity, Recommendation } from '../types';


const ActivityDetail: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const [activity, setActivity] = useState<Activity | null>(null);
  const [recommendation, setRecommendation] = useState<Recommendation | null>(null);

  useEffect(() => {
    const fetchActivityDetail = async () => {
      if (!id) return;
      try {
        const response = await getActivityDetail(id);
        setActivity(response.data);
        setRecommendation(response.data.recommendation || response.data);
      } catch (error) {
        console.error(error);
      }
    };

    fetchActivityDetail();
  }, [id]);

  if (!activity) {
    return <Typography sx={{ p: 4, textAlign: 'center' }}>Loading...</Typography>;
  }

  return (
    <Box sx={{ maxWidth: 900, mx: 'auto', p: 2 }}>
      <Card sx={{ mb: 4, overflow: 'hidden', boxShadow: 3 }}>
        <CardMedia
          component="img"
          height="300"
          image={getActivityImage(activity.activityType)}
          alt={activity.activityType}
        />
        <CardContent sx={{ p: 4 }}>
          <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 3 }}>
            <Typography variant="h4" fontWeight="bold">{activity.activityType}</Typography>
            <Typography variant="subtitle1" color="text.secondary">
              {activity.startTime || activity.createdAt 
                ? new Date(activity.startTime || activity.createdAt as string).toLocaleString() 
                : ''}
            </Typography>
          </Box>
          
          <Grid container spacing={3} sx={{ mb: 3 }}>
            <Grid item xs={6} sm={3}>
              <Typography color="text.secondary">Duration</Typography>
              <Typography variant="h6">{activity.duration} min</Typography>
            </Grid>
            <Grid item xs={6} sm={3}>
              <Typography color="text.secondary">Calories</Typography>
              <Typography variant="h6">{activity.caloriesBurned} kcal</Typography>
            </Grid>
          </Grid>

          {activity.additionalMetrics && Object.keys(activity.additionalMetrics).length > 0 && (
            <Box sx={{ mt: 2 }}>
              <Typography variant="subtitle2" color="text.secondary" gutterBottom>Additional Metrics</Typography>
              <Box sx={{ display: 'flex', gap: 1, flexWrap: 'wrap' }}>
                {Object.entries(activity.additionalMetrics).map(([key, val]) => (
                  <Chip key={key} label={`${key}: ${val}`} variant="outlined" color="primary" />
                ))}
              </Box>
            </Box>
          )}
        </CardContent>
      </Card>

      {recommendation && recommendation.analysis && (
        <Card sx={{ boxShadow: 3 }}>
          <CardContent sx={{ p: 4 }}>
            <Typography variant="h5" gutterBottom fontWeight="bold" color="primary">AI Analysis & Recommendations</Typography>
            
            <Box sx={{ mt: 3 }}>
              <Typography variant="h6" gutterBottom>Detailed Analysis</Typography>
              {Object.entries(recommendation.analysis).map(([key, text]) => (
                <Typography key={key} paragraph sx={{ mb: 1 }}>
                  <strong>{key.charAt(0).toUpperCase() + key.slice(1)}:</strong> {text}
                </Typography>
              ))}
            </Box>

            <Divider sx={{ my: 3 }} />

            {recommendation.improvements && recommendation.improvements.length > 0 && (
              <Box>
                <Typography variant="h6" gutterBottom>Areas for Improvement</Typography>
                {recommendation.improvements.map((imp, index) => (
                  <Box key={index} sx={{ mb: 2, pl: 2, borderLeft: '3px solid #ff9800' }}>
                    <Typography variant="subtitle1" fontWeight="bold">{imp.area}</Typography>
                    <Typography variant="body2">{imp.recommendation}</Typography>
                  </Box>
                ))}
              </Box>
            )}

            <Divider sx={{ my: 3 }} />

            {recommendation.suggestions && recommendation.suggestions.length > 0 && (
              <Box>
                <Typography variant="h6" gutterBottom>Workout Suggestions</Typography>
                {recommendation.suggestions.map((sug, index) => (
                  <Box key={index} sx={{ mb: 2, pl: 2, borderLeft: '3px solid #4caf50' }}>
                    <Typography variant="subtitle1" fontWeight="bold">{sug.workout}</Typography>
                    <Typography variant="body2">{sug.description}</Typography>
                  </Box>
                ))}
              </Box>
            )}

            <Divider sx={{ my: 3 }} />

            {recommendation.safety && recommendation.safety.length > 0 && (
              <Box>
                <Typography variant="h6" gutterBottom color="error.main">Safety Guidelines</Typography>
                <ul style={{ margin: 0, paddingLeft: '20px' }}>
                  {recommendation.safety.map((safeText, index) => (
                    <li key={index}>
                      <Typography variant="body2" sx={{ mb: 1 }}>{safeText}</Typography>
                    </li>
                  ))}
                </ul>
              </Box>
            )}
          </CardContent>
        </Card>
      )}
    </Box>
  );
};

export default ActivityDetail;