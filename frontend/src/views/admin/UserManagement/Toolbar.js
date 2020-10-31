import React from 'react';
import PropTypes from 'prop-types';
import clsx from 'clsx';
import {
  Box, Button, Modal, Fade, makeStyles,Backdrop, TextField,
  FormControl, Select, InputLabel, MenuItem
} from '@material-ui/core';
import { addUser } from '../../../api/instructorAPI';


const useStyles = makeStyles((theme) => ({
  modal: {
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  paper: {
    backgroundColor: theme.palette.background.paper,
    border: '2px solid #000',
    boxShadow: theme.shadows[5],
    padding: theme.spacing(2, 4, 3),
  },
  button: {
    '& > *': {
      margin: theme.spacing(1),
    }},
}));

const Toolbar = ({ className, ...rest }) => {
  const classes = useStyles();
  const [open, setOpen] = React.useState(false);
  const [usertype, setUsertype] = React.useState('');
  const [values, setValues] = React.useState({
    role:null,
    userName:'',
    passWord:'',
    id: 0
  });

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = (prop) => (event) => {
		setValues({ ...values, [prop]: event.target.value });
	};

  const handleSubmit = async() => {
		console.log(values);
		await addUser(values).then((res) => {
		  if (res.data == false) {
			alert('Please login to continue.');
			window.location.replace('/');
		  } else {
			window.location.reload();
		  }
		})
		.catch((error) => {
		  alert(error + ', Please login to continue.');
		  window.location.replace('/');
		});
	   
	  }

  return (
    <div
      className={clsx(classes.root, className)}
      {...rest}
    >
      <Box display="flex" justifyContent="flex-end">
        <Button color="primary" variant="contained" onClick={handleOpen}>
          Add User
        </Button>
        <Modal
            aria-labelledby="transition-modal-title"
            aria-describedby="transition-modal-description"
            className={classes.modal}
            open={open}
            closeAfterTransition
            BackdropComponent={Backdrop}
        >
            <Fade in={open}>
                <div className={classes.paper}>
                    <h2 style={{fontFamily: 'Arial', textAlign:'center'}}> Add a User </h2>
                    <FormControl variant="outlined" fullWidth className={classes.formControl} style={{ marginTop:12}}>
                        <InputLabel id="usertype-label">Role</InputLabel>
                        <Select
                            labelId="usertype-label"
                            id="usertype-outlined"
                            value={values.role}
                            onChange={handleChange('role')}
                            label="User Type"
                        >
                        <MenuItem value="INSTRUCTOR">Instructor</MenuItem>
                        <MenuItem value="STUDENT">Student</MenuItem>
                        </Select>
                    </FormControl>
                    <TextField
                        id="outlined-multiline-flexible"
                        required
                        fullWidth
                        value={values.userName}
                        onChange={handleChange('userName')}
                        label="User Name"
                        variant="outlined"
                        style={{ marginTop:12}}
                    />
                    <TextField
                        id="outlined-multiline-flexible"
                        required
                        fullWidth
                        value={values.passWord}
                        onChange={handleChange('passWord')}
                        label="Password"
                        variant="outlined"
                        style={{ marginTop:12}}
                    />
                    <div className={classes.button}>
                        <Button size="small" variant="contained"  onClick={handleClose} style={{marginLeft:120,marginTop:12}}>
                                    Cancel
                        </Button>
                        <Button size="small" variant="contained" onClick={handleSubmit} color="secondary" style={{marginLeft:20,marginTop:12}}>
                                    Submit
                        </Button>
                    </div>
                </div>
            </Fade>
      </Modal>
      </Box>
    </div>
  );
};

Toolbar.propTypes = {
  className: PropTypes.string
};

export default Toolbar;
