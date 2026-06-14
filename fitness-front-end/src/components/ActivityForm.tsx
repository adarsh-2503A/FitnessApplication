import {
  Box,
  Button,
  FormControl,
  InputLabel,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";
import { useState, type FC, type FormEvent } from "react";
import { addActivity } from "../Api";

export interface Activity {
  activityType: string;
  duration: string;
  caloriesBurned: string;
  additionalMetrics: Object;
}

interface ParamType{
  onActivityAdded:()=>void;
}

const ActivityForm: FC<ParamType> = ({onActivityAdded}:ParamType) => {
  const [activity, setActivity] = useState<Activity>({
    activityType: "RUNNING",
    duration: "",
    caloriesBurned: "",
    additionalMetrics: {},
  });

  async function handleSubmit(e: FormEvent<HTMLFormElement>) {
    e.preventDefault();
    try {
      await addActivity(activity);
      onActivityAdded();
      setActivity({
        activityType: "RUNNING",
        duration: "",
        caloriesBurned: "",
        additionalMetrics: {},
      });
    } catch (error) {}
  }

  return (
    <Box component={"form"} onSubmit={handleSubmit} sx={{ mb: 4 }}>
      <FormControl fullWidth sx={{ mb: 2 }}>
        <InputLabel>Activity Type</InputLabel>
        <Select
          value={activity.activityType}
          onChange={(e) =>
            setActivity({ ...activity, activityType: e.target.value })
          }
        >
          <MenuItem value="RUNNING">Running</MenuItem>
          <MenuItem value="WALKING">Walking</MenuItem>
          <MenuItem value="CYCLING">Cycling</MenuItem>
        </Select>
      </FormControl>
      <TextField
        fullWidth
        label="Duration (Minutes)"
        type="number"
        sx={{ mb: 2 }}
        value={activity.duration}
        onChange={(e) => setActivity({ ...activity, duration: e.target.value })}
      />

      <TextField
        fullWidth
        label="Calories Burned"
        type="number"
        sx={{ mb: 2 }}
        value={activity.caloriesBurned}
        onChange={(e) =>
          setActivity({ ...activity, caloriesBurned: e.target.value })
        }
      />

      <Button type="submit" variant="contained">
        Add Activity
      </Button>
    </Box>
  );
};

export default ActivityForm;
