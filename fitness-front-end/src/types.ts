export interface Activity {
  id?: number;
  userId?: string;
  activityType: string;
  duration: number | string;
  caloriesBurned: number | string;
  additionalMetrics?: Record<string, any>;
  startTime?: string;
  createdAt?: string;
  updatedAt?: string;
}

export interface Improvement {
  area: string;
  recommendation: string;
}

export interface Suggestion {
  workout: string;
  description: string;
}

export interface Recommendation {
  id?: number;
  userId?: string;
  activityId?: number;
  activityType?: string;
  analysis?: Record<string, string>;
  improvements?: Improvement[];
  suggestions?: Suggestion[];
  safety?: string[];
}