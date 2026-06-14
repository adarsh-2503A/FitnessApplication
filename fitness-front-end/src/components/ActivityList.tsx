import { useEffect, useState, type FC } from "react";
import { getActivities } from "../Api";
import type { Activity } from "./ActivityForm";

const ActivityList:FC=()=>{
    const [activities,setActivities]=useState<Activity[]>([]);
    useEffect(()=>{
        getActivities().then((res)=>{setActivities(res.data)});
    },[])
    return (
        <>
            <ol>
                {activities.map((act)=><li>{act.activityType}</li>)}
            </ol>
        </>
    )
}

export default ActivityList;