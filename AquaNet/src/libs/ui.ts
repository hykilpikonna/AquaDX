import {
    Chart as ChartJS,
    Title,
    Tooltip,
    Legend,
    LineElement,
    LinearScale,
    PointElement,
    CategoryScale, TimeScale,
} from 'chart.js';

export function title(t: string) {
    document.title = `AquaNet - ${t}`
}

export function registerChart() {
    ChartJS.register(
        Title,
        Tooltip,
        Legend,
        LineElement,
        LinearScale,
        PointElement,
        CategoryScale,
        TimeScale
    );
}