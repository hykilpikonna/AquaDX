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
import moment from "moment/moment";
// @ts-ignore
import CalHeatmap from "cal-heatmap";
// @ts-ignore
import CalTooltip from 'cal-heatmap/plugins/Tooltip';

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

export function renderCal(el: HTMLElement, d: {date: any, value: any}[]) {
    const cal = new CalHeatmap();
    return cal.paint({
        itemSelector: el,
        domain: {
            type: 'month',
            label: { text: 'MMM', textAlign: 'start', position: 'top' },
        },
        subDomain: {
            type: 'ghDay',
            radius: 2, width: 11, height: 11, gutter: 4
        },
        range: 12,
        data: {source: d, x: 'date', y: 'value'},
        scale: {
            color: {
                type: 'linear',
                range: ['#14432a', '#4dd05a'],
                domain: [0, d.reduce((a, b) => Math.max(a, b.value), 0)]
            },
        },
        date: {start: moment().subtract(1, 'year').add(1, 'month').toDate()},
        theme: "dark",
    }, [
        [CalTooltip, {text: (_: Date, v: number, d: any) =>
                `${v ?? "No"} songs played on ${d.format('MMMM D, YYYY')}`}]
    ]);
}

