import React, {Component} from 'react'
import CourseDataService from '../services/CourseDataService';

const INSTRUCTOR = 'in28minutes'
// const COURSE_API_URL = 'http://localhost:8080'
// const INSTRUCTOR_API_URL =
//     `${COURSE_API_URL}/instructions/${INSTRUCTOR}`

class ListCoursesComponent extends Component {

    constructor(props){
        super(props)
        this.state = {
            courses: [],
            message: null
        }
        this.refreshCourses = this.refreshCourses.bind(this)
        this.updateCourseClicked = this.updateCourseClicked.bind(this)
        this.deleteCourseClicked = this.deleteCourseClicked.bind(this)
        this.addCourseClicked = this.addCourseClicked.bind(this)
    }

    componentDidMount() {
        this.refreshCourses();
    }

    refreshCourses() {
        CourseDataService.retrieveAllCourses(INSTRUCTOR) //NOT RECOGNIZED?
            .then(
                response => {
                    console.log(response);
                    this.setState({courses: response.data})
                }
            )
    }

    updateCourseClicked(id) {
        console.log('update ' + id);
        this.props.history.push(`/courses/${id}`)
    }

    deleteCourseClicked(id) {
        CourseDataService.deleteCourse(INSTRUCTOR, id)
            .then(
                response => {
                    this.setState({ message: `Delete of course ${id} Successful` })
                    this.refreshCourses()
                }
            )
    }

    addCourseClicked() {
        this.props.history.push(`/courses/-1`)
    }

    render(){
        return(
            <div className="container">
                <h3>All Courses</h3>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Description</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.courses.map(
                                    course =>
                                        <tr key={course.id}>
                                            <td>{course.id}</td>
                                            <td>{course.description}</td>
                                            <td><button className="btn btn-warning" onClick={() => this.deleteCourseClicked(course.id)}>Delete</button></td>
                                            <td><button className="btn btn-success" onClick={() => this.updateCourseClicked(course.id)}>Update</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <button className="btn btn-success" onClick={this.addCourseClicked}>Add</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default ListCoursesComponent