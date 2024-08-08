import { onMount } from 'svelte';

const useLocalStorage = <T>(key: string, initialValue: T) => {
  let value = initialValue;

  const currentValue = localStorage.getItem(key);
  if (currentValue) value = JSON.parse(currentValue);

  const save = () => {
    localStorage.setItem(key, JSON.stringify(value));
  };

  return {
    get value() {
      return value;
    },
    set value(v: T) {
      value = v;
      save();
    },
  };
};

export default useLocalStorage;
