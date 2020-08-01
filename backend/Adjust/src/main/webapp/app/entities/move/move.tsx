import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './move.reducer';
import { IMove } from 'app/shared/model/move.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMoveProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Move = (props: IMoveProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { moveList, match, loading } = props;
  return (
    <div>
      <h2 id="move-heading">
        <Translate contentKey="adjustApp.move.home.title">Moves</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="adjustApp.move.home.createLabel">Create new Move</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {moveList && moveList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.move.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.move.muscleName">Muscle Name</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.move.muscleType">Muscle Type</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.move.equipment">Equipment</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.move.picture">Picture</Translate>
                </th>
                <th>
                  <Translate contentKey="adjustApp.move.adjustMoveId">Adjust Move Id</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {moveList.map((move, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${move.id}`} color="link" size="sm">
                      {move.id}
                    </Button>
                  </td>
                  <td>{move.name}</td>
                  <td>{move.muscleName}</td>
                  <td>
                    <Translate contentKey={`adjustApp.MuscleType.${move.muscleType}`} />
                  </td>
                  <td>{move.equipment}</td>
                  <td>
                    {move.picture ? (
                      <div>
                        {move.pictureContentType ? (
                          <a onClick={openFile(move.pictureContentType, move.picture)}>
                            <img src={`data:${move.pictureContentType};base64,${move.picture}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {move.pictureContentType}, {byteSize(move.picture)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{move.adjustMoveId}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${move.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${move.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${move.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="adjustApp.move.home.notFound">No Moves found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ move }: IRootState) => ({
  moveList: move.entities,
  loading: move.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Move);
