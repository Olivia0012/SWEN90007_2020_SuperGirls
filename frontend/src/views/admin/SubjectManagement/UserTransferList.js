import React, { useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import List from '@material-ui/core/List';
import Card from '@material-ui/core/Card';
import CardHeader from '@material-ui/core/CardHeader';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import Checkbox from '@material-ui/core/Checkbox';
import Button from '@material-ui/core/Button';
import Divider from '@material-ui/core/Divider';
import { getUsers, addUserInSubject } from '../../../api/instructorAPI';

const useStyles = makeStyles((theme) => ({
	root: {
		margin: 'auto'
	},
	cardHeader: {
		padding: theme.spacing(1, 2)
	},
	list: {
		width: 200,
		height: 230,
		backgroundColor: theme.palette.background.paper,
		overflow: 'auto'
	},
	button: {
		margin: theme.spacing(0.5, 0)
	}
}));

function not(a, b) {
	return a.filter((value) => b.indexOf(value) === -1);
}

function intersection(a, b) {
	return a.filter((value) => b.indexOf(value) !== -1);
}

function union(a, b) {
	return [ ...a, ...not(b, a) ];
}

export default function UserTransferList(props) {
	const classes = useStyles();
	const [ checked, setChecked ] = React.useState([]);
	const [ left, setLeft ] = React.useState([ 0, 1, 2, 3 ]);
	const [ right, setRight ] = React.useState([]);
	const { role, id } = props;
  
  
  const handleClose = () => {
		window.location.reload();
  };
  
  const handleSubmit = async() => {
    await addUserInSubject(right,id,role).then((res) => {
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

	useEffect(() => {
		const fetchData = async () => {
			//setLoading(true);
			const result = await getUsers(role, id)
				.then((res) => {
					console.log(res.data);
					if (res.data == false) {
						alert('Please login to continue.');
						window.location.replace('/');
					} else {
						setLeft(
							res.data.unenrolled.map((user) => {
								return user;
							})
            );
            setRight(
              res.data.enrolled.map((user) => {
								return user;
							})
            )
					}
				})
				.catch((error) => {
					alert(error + 'Please login to continue.');
					window.location.replace('/');
				});
		};

		fetchData();
	}, []);

	const leftChecked = intersection(checked, left);
	const rightChecked = intersection(checked, right);

	const handleToggle = (value) => () => {
		const currentIndex = checked.indexOf(value);
		const newChecked = [ ...checked ];

		if (currentIndex === -1) {
			newChecked.push(value);
		} else {
			newChecked.splice(currentIndex, 1);
		}

		setChecked(newChecked);
	};

	const numberOfChecked = (items) => intersection(checked, items).length;

	const handleToggleAll = (items) => () => {
		if (numberOfChecked(items) === items.length) {
			setChecked(not(checked, items));
		} else {
			setChecked(union(checked, items));
		}
	};

	const handleCheckedRight = () => {
		setRight(right.concat(leftChecked));
		setLeft(not(left, leftChecked));
		setChecked(not(checked, leftChecked));
	};

	const handleCheckedLeft = () => {
		setLeft(left.concat(rightChecked));
		setRight(not(right, rightChecked));
		setChecked(not(checked, rightChecked));
  };
  
	const customList = (title, items) => (
		<Card>
			<CardHeader
				className={classes.cardHeader}
				avatar={
					<Checkbox
						onClick={handleToggleAll(items)}
						checked={numberOfChecked(items) === items.length && items.length !== 0}
						indeterminate={numberOfChecked(items) !== items.length && numberOfChecked(items) !== 0}
						disabled={items.length === 0}
						inputProps={{ 'aria-label': 'all items selected' }}
					/>
				}
				title={title}
				subheader={`${numberOfChecked(items)}/${items.length} selected`}
			/>
			<Divider />
			<List className={classes.list} dense component="div" role="list">
				{items.map((value) => {
					const labelId = `transfer-list-all-item-${value}-label`;

					return (
						<ListItem key={value} role="listitem" button onClick={handleToggle(value)}>
							<ListItemIcon>
								<Checkbox
									checked={checked.indexOf(value) !== -1}
									tabIndex={-1}
									disableRipple
									inputProps={{ 'aria-labelledby': labelId }}
								/>
							</ListItemIcon>
							<ListItemText id={labelId} primary={`${value.userName}`} />
						</ListItem>
					);
				})}
				<ListItem />
			</List>
		</Card>
	);

	return (
		<div>
			<Grid container spacing={2} justify="center" alignItems="center" className={classes.root}>
				<Grid item>{customList('Unchosen', left)}</Grid>
				<Grid item>
					<Grid container direction="column" alignItems="center">
						<Button
							variant="outlined"
							size="small"
							className={classes.button}
							onClick={handleCheckedRight}
							disabled={leftChecked.length === 0}
							aria-label="move selected right"
						>
							&gt;
						</Button>
						<Button
							variant="outlined"
							size="small"
							className={classes.button}
							onClick={handleCheckedLeft}
							disabled={rightChecked.length === 0}
							aria-label="move selected left"
						>
							&lt;
						</Button>
					</Grid>
				</Grid>
				<Grid item>{customList('Chosen', right)}</Grid>
			</Grid>
			<Button size="small" variant="contained" onClick={handleClose} style={{ marginLeft: 180, marginTop: 12 }}>
				Cancel
			</Button>
			<Button size="small" variant="contained"  onClick={handleSubmit} color="secondary" style={{ marginLeft: 20, marginTop: 12 }}>
				Submit
			</Button>
		</div>
	);
}
