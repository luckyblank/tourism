/**
 * Resource class
 */

import { axios } from '../utils'

export default class Resource {
  constructor () {
    this.axios = axios
  }

  get (url, id, options) {
    if (typeof id === 'object') {
      options = id
    } else if (id !== undefined) {
      url += `?id=${id}`
    }
    return this.axios.get(url, options)
  }

  post (url, options) {
    return this.axios.post(url, options)
  }

  put (url, id, options) {
    return this.axios.put(url + '/' + id, options)
  }

  patch (url, id, options) {
    debugger
    return this.axios.patch(url + '/' + id, options)
  }

  delete (url, id, options) {
    return this.axios.delete(url + '/' + id, options)
  }
}
