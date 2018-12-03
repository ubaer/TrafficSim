package com.ai.trafficsim;

import Vehicle.*;

import java.util.*;
import java.util.stream.Collectors;

public class Statistics {
    private List<Vehicle> currentVehicles;
    private List<Vehicle> finishedVehicles;
    private List<List<Vehicle>> crashedVehicles;
    private List<Class<?>> vehicleTypes = Arrays.asList(Car.class, Bus.class, Bicycle.class);

    public Statistics(List<Vehicle> vehicles) {
        currentVehicles = vehicles;
        crashedVehicles = new ArrayList<List<Vehicle>>();
        finishedVehicles = new ArrayList<Vehicle>();
    }

    public List<Integer> GetCrashData() {
        // todo add crash data
        return Arrays.asList(1, 2, 3);
    }

    public void Despawn(Vehicle v) {
        v.Despawn();
        finishedVehicles.add(v);
    }

    public List<ChartData> GetCurrentVehicles() {
        List<ChartData> data = new ArrayList<>();

        vehicleTypes.forEach(type -> {
            Double currentVehicles = ((double) this.currentVehicles.stream().
                    filter(vehicle -> vehicle.getClass() == type)
                    .count());
            data.add(new ChartData(Arrays.asList(currentVehicles), type.getSimpleName()));
        });

        return data;
    }

    public List<ChartData> GetRouteFinished() {
        List<ChartData> data = new ArrayList<>();

        vehicleTypes.forEach(type -> {
            double vehicleFinished = ((double) finishedVehicles.stream().
                    filter(vehicle -> vehicle.getClass() == type)
                    .count());
            data.add(new ChartData(Arrays.asList(vehicleFinished), type.getSimpleName()));
        });

        return data;
    }

    public List<ChartData> GetWaitTime() {
        List<ChartData> data = new ArrayList<>();

        vehicleTypes.forEach(type -> {
            List<Vehicle> vehicleSublist = finishedVehicles.stream().
                    filter(vehicle -> vehicle.getClass() == type).
                    collect(Collectors.toList());
            double totalUptime = vehicleSublist.stream().mapToDouble(v -> v.GetUpTime()).sum();
            double averageUptime = totalUptime / vehicleSublist.size();

            double highestUptime = Double.NaN;
            double lowestUptime = Double.NaN;

            try {
                highestUptime = vehicleSublist.stream().max(Comparator.comparingDouble(Vehicle::GetUpTime)).get().GetUpTime();
            } catch (Exception e) {
            }
            try {
                lowestUptime = vehicleSublist.stream().min(Comparator.comparingDouble(Vehicle::GetUpTime)).get().GetUpTime();
            } catch (Exception e) {
            }

            data.add(new ChartData(Arrays.asList(averageUptime, highestUptime, lowestUptime), type.getSimpleName()));
        });

        return data;
    }
}
