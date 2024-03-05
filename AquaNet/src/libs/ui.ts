import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  LineElement,
  LinearScale,
  PointElement,
  CategoryScale, TimeScale, type ChartOptions, type LineOptions,
} from 'chart.js'
import moment from 'moment/moment'
// @ts-expect-error Cal-heatmap does not have proper types
import CalHeatmap from 'cal-heatmap'
// @ts-expect-error Cal-heatmap does not have proper types
import CalTooltip from 'cal-heatmap/plugins/Tooltip'
import { DEFAULT_PFP } from "./config";

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
  )
}

export function renderCal(el: HTMLElement, d: {date: any, value: any}[]): Promise<any> {
  const cal = new CalHeatmap()
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
    data: { source: d, x: 'date', y: 'value' },
    scale: {
      color: {
        type: 'linear',
        range: [ '#14432a', '#4dd05a' ],
        domain: [ 0, d.reduce((a, b) => Math.max(a, b.value), 0) ]
      },
    },
    date: { start: moment().subtract(1, 'year').add(1, 'month').toDate() },
    theme: 'dark',
  }, [
    [ CalTooltip, { text: (_: Date, v: number, d: any) =>
      `${v ?? 'No'} songs played on ${d.format('MMMM D, YYYY')}` }]
  ])
}


const now = moment()
export const CHARTJS_OPT: ChartOptions<'line'> = {
  responsive: true,
  maintainAspectRatio: false,
  // TODO: Show point on hover
  elements: {
    point: {
      radius: 0
    }
  },
  scales: {
    xAxis: {
      type: 'time',
      display: false
    },
    y: {
      display: false,
    }
  },
  plugins: {
    legend: {
      display: false
    },
    tooltip: {
      mode: 'index',
      intersect: false,
      callbacks: {
        title: (tooltipItems) => {
          const date = tooltipItems[0].parsed.x;
          const diff = now.diff(date, 'days')
          return diff ? `${diff} days ago` : 'Today'
        }
      }
    }
  },
}

export const pfpNotFound = (e: Event) => (e.target as HTMLImageElement).src = DEFAULT_PFP
export const coverNotFound = (e: Event) => (e.target as HTMLImageElement).src = "/assets/imgs/no_cover.jpg"
